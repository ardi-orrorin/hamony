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

export const useDairy = defineStore('diary', {
    state(): Diary {
        return {
            id: null,
            subject: '',
            content: '',
            createAt: new Date(),
            updateAt: null,
            deleteAt: null,
        }
    },
})