import axios from "axios";
import type {DiaryTag} from "@/store/diary";
import {useDairys, useDiary} from "@/store/diary";
import {useToken} from "@/store/member";


export async function getDiary(id: string) {
    const reuslt = await axios.get(import.meta.env.VITE_API_URL + '/diary/'+id, {headers: {
            Authorization: useToken().tokenType + " " + useToken().token
        }})
        .then(res => res.data)
        .catch(err => err.response)

    if(reuslt.status === 200) {
        console.log(reuslt.data)
        const data = useDiary()
        data.$reset()
        data.add(reuslt.data)
    }

    return reuslt
}

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


    return result
}

export async function profileList(option: string) {

    console.log(option);

    const result = await axios.get(import.meta.env.VITE_API_URL + '/profile/' + option, {headers: {
            Authorization: useToken().tokenType + ' ' + useToken().token
        }})
        .then(res => res.data)
        .catch(err => err.response)

    if(result.status === 200){
        const data = useDairys()
        data.add(result.data)
    }

    return result;

}

