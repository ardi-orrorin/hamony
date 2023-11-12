<script lang="ts" setup>

import DiaryBody from "@/components/DiaryBody.vue";
import {ref, watchEffect} from "vue";
import IconBtn from "@/components/IconBtn.vue";
import koText from "@/assets/lang/ko-kr.json"
import DiarySubject from "@/components/DiarySubject.vue";
import DiaryItem from "@/components/DiaryItem.vue";
import {deleteDiary, writeDiary} from "@/api/diaryApi";
import type {DiaryTag} from "@/store/diary";
import {useDiary, useDiaryBody} from "@/store/diary";
import router from "@/router";


const isRead = ref<boolean>(false)
const diary = useDiary()
const text = koText
const value = useDiaryBody()

const imgRef = ref<HTMLInputElement | null>(null)
const previewRef = ref<string>('')
const previewImg = ref<boolean>(false)
const file = ref<File | null>(null);

watchEffect( async ()=>{
  isRead.value = router.currentRoute.value.meta.isRead  as boolean;

  if(!isRead.value)
    value.$reset()
    previewRef.value = '';

  if (value.file){
    previewRef.value = import.meta.env.VITE_API_URL + value.file;
  }
})


function previewToggle() {
  previewImg.value = !previewImg.value
}

function addImgHandler() {
  if (imgRef.value && !isRead.value) {
    imgRef.value.click()
  }
}
function addPreviewHandler(el: any) {
  previewRef.value = '';
  file.value = el.target.files[0];
  previewRef.value = URL.createObjectURL(file.value!!);
}

function findTag() {
  value.tag.clear()
  value.content
      .split('\n')
      .join(' ')
      .split(" ")
      .filter(it => (
          (/^#[a-zA-Zㄱ-ㅎ가-핳]{2}/).test(it)
      )).forEach(it => {
        if(it.length > 10) return
        value.tag.add(it)
      })
}

function removeTagHandler(tag: string){
  if (!isRead.value){
    value.tag.delete(tag)
    value.content = value.content.replaceAll(tag, tag.slice(1))
  }
}

function btnUrlHandler(index: number, isAlert: boolean){
  if(index + 1 < value.url.length){
    value.url.splice(index, 1)
  } else{
    if(value.url[index].length > 5) {
      if(value.url.length < 4){
        value.url.push('')
      } else {
        isAlert && alert(text.alertUrl4Max)
      }
    } else{
      isAlert && alert(text.minTextUrl)
    }
  }
}

function onSubmit(){

  const data: DiaryTag = {
    diary: {
      subject: value.subject,
      content: value.content,
    },
    tags: [...value.tag.values()].map(it => ({tag: it})),
    urls: value.url.map(it => ({url: it})).filter(it => it.url.length > 5)
  }


  const formData = new FormData()
  formData.append('diary', new Blob([JSON.stringify(data)], {type: "application/json"}))

  if(file.value){
    formData.append('file', file.value)
  }

  writeDiary(formData)
      .then(res => {
        if(res.status === 201){
          router.push("/read/"+ res.data)
        }
      })
}

function deleteHandler(){
  if(confirm("삭제하시겠습니까?")){
    deleteDiary(value.id!!)
        .then(res => {
          if(res.status === 200){
            router.push("/")
          }
        })
  }
}

console.log(isRead.value)
console.log(value.isModify)
</script>

<template>
  <div class="container">
    <div class="subContianer">
      <div class="info">
        <DiarySubject
            v-model:value="value.subject"
            style="border-radius: 10px"
            :placeholder="text.enterSubject"
            :disabled="isRead"
        />
      </div>
      <div class="contentBody">
        <DiaryBody
            v-model:value="value.content"
            :placeholder="text.enterBody"
            style="border-radius: 12px"
            @keyup="findTag"
            :disabled="isRead"
        />
        <Transition>
          <div
              class="sticky"
              v-if="value.content.length > 10 && value.subject.length > 2"
          >
            <IconBtn text="edit" @click="onSubmit" :hidden="isRead && !value.isModify"/>
          </div>
        </Transition>
        <Transition>
          <div
              class="stickyEdit"
              v-if="isRead && value.isModify"
          >
            <IconBtn text="delete" @click="deleteHandler" :hidden="isRead && !value.isModify"/>
          </div>
        </Transition>
        <Transition>
          <div class="textSizeSticky">
            {{value.content.length}} 자
          </div>
        </Transition>
        <div class="imgSticky" @click="addImgHandler" @mouseover="previewToggle" >
          <input ref="imgRef" type="file" @change="addPreviewHandler" hidden/>
          <img :src="previewRef" onerror="this.src='/imgerror.png'" />
        </div>
        <Transition>
          <div v-if="previewImg && previewRef.length > 0" class="imgPreview" @click="addImgHandler">
            <img :src="previewRef" onerror="this.src='/imgerror.png'" @mouseleave="previewToggle"/>
          </div>
        </Transition>
      </div>
      <div v-for="(url, index) in value.url">
        <div class="info">
            <template v-if="isRead">
              <a class="url" :href="`https://${url}`" target="_blank">https://{{url}}</a>
            </template>
            <template v-else>
              <DiarySubject
                  style="border-radius: 10px"
                  :placeholder="text.enterUrl"
                  v-model:value="value.url[index]"
                  @change="btnUrlHandler(index, false)"
                  :disabled="isRead"
                  :link="isRead"
              />
            </template>
          <Transition>
          <div class="urlSticky">
            <button @click="btnUrlHandler(index, true)" :hidden="isRead">
            {{ index + 1 < value.url.length ? '-' : '+' }}
            </button>
          </div>
          </Transition>
        </div>
      </div>
      <div v-if="value.tag.size > 0" class="footer">
        <template v-for="tag in value.tag" >
          <DiaryItem :text="tag" @click="removeTagHandler(tag)" :diabled="isRead"/>
        </template>
      </div>
      <div v-else class="footer tagIntro">
        {{text.tagIntro}}
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
  .container {

    display: flex;
    justify-content: center;
    align-items: center;
  }

  .subContianer {
    min-width: 400px;
    width: 35vw;
    border-radius: 15px;
    box-shadow: 0 0 10px 5px rgba(230, 230, 230, 0.5);
    @media (max-width: 1000px) {
      width: 45vw;
    }
    @media (max-width: 800px) {
      width: 60vw;
    }
  }


  .info {
    padding: 10px;
    display: flex;
    height: 10%;
    align-items: center;
    justify-content: center;
    position: relative;
  }

  .contentBody {
    padding: 10px;
    min-height: 300px;
    height: 50vh;
    position: relative;
  }

  .textSizeSticky {
    position: absolute;
    bottom: 2vh;
    right: 2.3vw;
    color: gray;
    font-size: 0.85rem;
  }
  .sticky {
    position: absolute;
    bottom: 5vh;
    right: 1.5vw;
  }

  .stickyEdit {
    position: absolute;
    bottom: 5vh;
    right: 4.5vw;
  }

  .footer {
    //display: flex;
    height: 10%;
    width: 100%;
    padding: 5px;
  }

  .tagIntro {
    color: gray;
    font-size: 0.8rem;
    padding: 10px 20px;
  }

  .urlSticky {
    position: absolute;
    right: 1.2vw;

    button {
      font-size: 1.1rem;
      color: gray;
    }
  }

  .imgSticky {
    position: absolute;
    left: 20px;
    bottom: 20px;
    width: 80px;
    height: 80px;
    border-radius: 100vw;

    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      border-radius: 100%;
    }
    border: 3px solid rgba(230, 230, 230, 1);
  }

  .imgPreview {
    position: absolute;
    left: -25%;
    bottom: 0;
    width: 50vw;
    height: 50vh;
    box-shadow: 0 0 10px 5px rgba(230, 230, 230, 1);
    border-radius: 1vw;
    img {
      width: 100%;
      height: 100%;
      object-fit: contain;
      border-radius: 1vw;
      //border: 1px solid rgba(200, 200, 200, 1);
      background: white;
    }
  }

  .url {
    color: #3f51b5;
    text-decoration: none;
    font-size: 0.9rem;
    font-weight: 600;
    width: 100%;
    word-break: break-all;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }

  .v-enter-active,
  .v-leave-active {
    transition: opacity 0.35s ease;
  }

  .v-enter-from,
  .v-leave-to {
    opacity: 0;
  }

</style>