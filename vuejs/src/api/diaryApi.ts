import axios, {type AxiosResponse} from "axios";
import type {Diary} from "@/store/diary";
import {useDairys, useDiaryBody} from "@/store/diary";
import {useToken} from "@/store/member";
import {isLogout} from "@/api/memberApi";



export async function getDiary(id: string) {
    const result = await axios.get(import.meta.env.VITE_API_URL + '/diary/'+id, {headers: {
            Authorization: useToken().tokenType + " " + useToken().token
        }})
        .then(res => res.data)
        .catch(err => err.response)


    isLogout(result.status)

    if(result.status === 200) {
        const data = useDiaryBody()
        data.$reset()
        data.addApiData(result)
    }

    return result
}

//검색하는 api 함수
export async function searchDiary(search: string) {
    const data = useDairys()
    data.$reset()


    const result = await axios.get(import.meta.env.VITE_API_URL + '/diary/search/' + search, {headers: {
            Authorization: useToken().tokenType + " " + useToken().token
        }})
        .then(res => res.data)
        .catch(err => err.response)

    isLogout(result.status)

    if(result.status === 200){
        data.add(result.data)
    }

    return result
}

export async function writeDiary(data: FormData) {

    const result = await axios.post(import.meta.env.VITE_API_URL + '/diary/write', data, {headers: {
        Authorization: useToken().tokenType + " " + useToken().token
        }})
        .then(res => res.data)
        .catch(err => err.response)

    isLogout(result.status)

    return result
}

export async function recentDiary(): Promise<AxiosResponse<Diary[]>> {
    const result = await axios.get(import.meta.env.VITE_API_URL + '/diary/recent?sort=createAt,desc', {headers: {
        Authorization: useToken().tokenType + ' ' + useToken().token
        }})
        .then(res => res.data)
        .catch(err => err.response)

    isLogout(result.status)

    return result
}

export async function profileList(option: string) {

    const result = await axios.get(import.meta.env.VITE_API_URL + '/profile/' + option, {headers: {
            Authorization: useToken().tokenType + ' ' + useToken().token
        }})
        .then(res => res.data)
        .catch(err => err.response)

    isLogout(result.status)

    if(result.status === 200){
        const data = useDairys()
        data.add(result.data)
    }

    return result;
}

export async function imgUpload(data: FormData){

    const result = await axios.post(import.meta.env.VITE_API_URL + '/api/file/upload', data, {headers: {
            Authorization: useToken().tokenType + ' ' + useToken().token
        }})
        .then(res => res.data)
        .catch(err => err.response)

    isLogout(result.status)

    return result
}


export async function getLike(id: string) {
    const result = await axios.get(import.meta.env.VITE_API_URL + '/like/' + id, {headers: {
            Authorization: useToken().tokenType + ' ' + useToken().token
        }})
        .then(res => res.data)
        .catch(err => err.response)

    isLogout(result.status)

    return result
}


export async function likeDiaryToggle(id: string) {
    const result = await axios.post(import.meta.env.VITE_API_URL + '/like/' + id,{}, {headers: {
            Authorization: useToken().tokenType + ' ' + useToken().token
        }})
        .then(res => res.data)
        .catch(err => err.response)

    isLogout(result.status)

    return result
}
