import axios from "axios";
import type {DiaryTag} from "@/store/diary";
import {useToken} from "@/store/member";


export async function writeDiary(data: DiaryTag) {

    const reuslt = await axios.post(import.meta.env.VITE_API_URL + '/diary/write', data, {headers: {
        Authorization: useToken().tokenType + " " + useToken().token
        }})
        .then(res => res.data)
        .catch(err => err.response)

    return reuslt
}

export async function recentDiary() {
    console.log(useToken().getInfo)
    const result = await axios.get(import.meta.env.VITE_API_URL + '/diary/recent', {headers: {
        Authorization: useToken().tokenType + ' ' + useToken().token
        }})
        .then(res => res.data)
        .catch(err => err.response)

    console.log(result)
    return result
}

