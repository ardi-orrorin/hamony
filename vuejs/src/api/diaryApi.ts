import axios from "axios";
import type {DiaryTag} from "@/store/diary";


export async function writeDiary(data: DiaryTag) {

    const reuslt = await axios.post(import.meta.env.VITE_API_URL + '/diary/write', data, {headers: {
        Authorization: "Bearer " + localStorage.getItem('token'),
        }})
        .then(res => res.data)
        .catch(err => err.response)

    return reuslt

}