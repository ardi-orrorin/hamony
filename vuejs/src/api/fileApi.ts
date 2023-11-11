import {useToken} from "@/store/member";
import axios from "axios";
import {isLogout} from "@/api/memberApi";

export async function fileLoad(path: string) {
    console.log(path)
    const result = await axios.get(import.meta.env.VITE_API_URL + path, {headers: {
            Authorization: useToken().tokenType + " " + useToken().token
        }})
        .then(res => res.data)
        .catch(err => err.response)

    isLogout(result.status)

    return result
}