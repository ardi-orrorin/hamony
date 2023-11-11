import type {Tag} from "@/store/tag";
import {defineStore} from "pinia";
import type {Url} from "@/store/url";

export interface Diary {
    id?: number | null;
    subject: string;
    content: string;
    createAt?: string | Date;
    updateAt?: string | Date | null;
    deleteAt?: string | Date | null;
    diaryTag?: Tag[]
}
export interface DiaryTag {
    id?: number | null | undefined
    diary: Diary
    tags?: Tag[]
    urls?: Url[]
}

interface Body {
    subject: string
    content: string
    tag: Set<string>
    url: string[]
    file: string
}

interface responseDiaryDTO {
    diary: Diary
    tag?: Tag[]
    url?: Url[]
    file: string
}

export const useDiary = defineStore('diary', {
    state(): Diary {
      return {id:null, subject: '', content: '', createAt: '', updateAt: null, deleteAt: null}
    },
    actions: {
        add(data: Diary): void {
            this.id = data.id;
            this.subject = data.subject;
            this.content = data.content;
            this.createAt = data.createAt;
            this.updateAt = data.updateAt;
            this.deleteAt = data.deleteAt;
        }
    }
})

export const useDairys = defineStore('diarys', {
    state(): {store: Diary[]} {
        return {
            store: []
        }
    },
    actions: {
        add(data: Diary[]): void {
            this.$state.store = [...this.$state.store, ...data]
        },
    }
})

export const useDiaryBody = defineStore('diaryBody', {
    state(): Body {
        return {subject: '', content: '', tag: new Set(), url: [''], file: "" }
    },
    actions: {
        add(data: Body): void {
            this.subject = data.subject;
            this.content = data.content;
            this.tag = data.tag;
            this.url = data.url;
        },
        addApiData(data: responseDiaryDTO): void {
                this.subject = data.diary.subject;
                this.content = data.diary.content;
                this.tag = data.tag ? new Set(data.tag.map(tag => tag.tag)) : new Set();
                this.url = data.url ? data.url.map(url => url.url) : [];
                this.file = data.file;
        }
    },
})