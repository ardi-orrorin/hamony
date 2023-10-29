import type {Tag} from "@/store/tag";
import {defineStore} from "pinia";

export interface Diary {
    id?: number | null;
    subject: string;
    content: string;
    createAt?: string | Date;
    updateAt?: string | Date | null;
    deleteAt?: string | Date | null;
}
export interface DiaryTag {
    id?: number | null | undefined
    diary: Diary
    tag?: Tag[]
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
    state(): Diary[] {
        return []
    },
    actions: {
        add(data: Diary[]): void {
            this.$state = [...this.$state, ...data]
        }
    }
})