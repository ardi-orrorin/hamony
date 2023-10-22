<script lang="ts" setup>

import DiaryBody from "@/components/DiaryBody.vue";
import {reactive} from "vue";
import IconBtn from "@/components/IconBtn.vue";
import koText from "@/assets/lang/ko-kr.json"
import DiarySubject from "@/components/DiarySubject.vue";
import DiaryItem from "@/components/DiaryItem.vue";
import {writeDiary} from "@/api/diaryApi";
import type {Diary, DiaryTag} from "@/store/diary";

interface Body {
  subject: string
  content: string
  tag: Set<string>
  url: string[]
}

const text = koText
const value = reactive<Body>({
  subject: '',
  content: '',
  tag: new Set(),
  url: ['']
})

function findTag() {
  value.tag.clear()
  value.content
      .split(' ')
      .filter(it => (
          (/^#[a-zA-Zㄱ-ㅎ가-핳]{2}/).test(it)
      )).forEach(it => {
        if(it.length > 10) return
        value.tag.add(it)
      })
}

function removeTagHandler(tag: string){
  value.tag.delete(tag)
  value.content = value.content.replaceAll(tag, tag.slice(1))
}

function btnUrlHandler(index: number) {
  if(index + 1 < value.url.length){
    value.url.splice(index, 1)
  } else{
    if(value.url[index].length > 5) {
      if(value.url.length < 4){
        value.url.push('')
      } else {
        alert(text.alertUrl4Max)
      }
    } else{
      alert(text.minTextUrl)
    }
  }
}

function onSubmit(){

  const data: DiaryTag = {
    diary: {
      subject: value.subject,
      content: value.content,
    },
    tag: [...value.tag.values()].map(it => ({tag: it}))
  }
  writeDiary(data)
}
</script>

<template>
  <div class="container">
    <div class="subContianer">
      <div class="info">
        <DiarySubject
            v-model:value="value.subject"
            style="border-radius: 10px"
            :placeholder="text.enterSubject"
        />
      </div>
      <div class="contentBody">
        <DiaryBody
            v-model:value="value.content"
            :placeholder="text.enterBody"
            style="border-radius: 12px"
            @keyup="findTag"
        />
        <Transition>
          <div
              class="sticky"
              v-if="value.content.length > 10 && value.subject.length > 2"
          >
            <IconBtn text="edit" @click="onSubmit"/>
          </div>
        </Transition>
        <Transition>
          <div class="textSizeSticky">
            {{value.content.length}} 자
          </div>
        </Transition>
      </div>
      <div v-for="(url, index) in value.url">
        <div class="info">
          <DiarySubject
              style="border-radius: 10px"
              :placeholder="text.enterUrl"
              v-model:value="value.url[index]"
          />
          <Transition>
          <div class="urlSticky">
            <button @click="btnUrlHandler(index)">
            {{ index + 1 < value.url.length ? '-' : '+' }}
            </button>
          </div>
          </Transition>
        </div>
      </div>
      <div v-if="value.tag.size > 0" class="footer">
        <template v-for="tag in value.tag" >
          <DiaryItem :text="tag" @click="removeTagHandler(tag)" />
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
    width: 35vw;
    border-radius: 15px;
    box-shadow: 0 0 10px 5px rgba(230, 230, 230, 0.5);
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

  .v-enter-active,
  .v-leave-active {
    transition: opacity 0.35s ease;
  }

  .v-enter-from,
  .v-leave-to {
    opacity: 0;
  }

</style>