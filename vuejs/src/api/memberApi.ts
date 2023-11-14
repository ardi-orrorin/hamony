import axios from "axios";
import {type User, useToken} from "@/store/member";
import router from "@/router";

export interface Login {
    userId: string,
    userPwd: string,
}

export async function deleteMember () {
    const result = await axios.delete(import.meta.env.VITE_API_URL+"/user/delete", { headers: {
            Authorization: useToken().tokenType + " " + useToken().token
        }})
        .then(res => res)
        .catch(err => err.response);

    if(result.status === 200) {
        useToken().$reset()
        router.push("/")
    }

    return result;
}

export async function login(data: Login) {
    const result = await axios.post(import.meta.env.VITE_API_URL+"/user/login", data)
        .then(res => res.data)
        .catch(err => err.response);

    if(result.status === 200)
        useToken().login(result.data)
    return result;
}

export async function signIn(data: User) {
    const result = await axios.post(import.meta.env.VITE_API_URL+"/user/signin", data)
        .then(res => res.data)
        .catch(err => err.response);

    return result;
}

export async function idDuplicateChk(userId: string){
    const result = await axios.post(import.meta.env.VITE_API_URL+"/user/idchk", userId)
        .then(res => res.data)
        .catch(err => err.response);

    return result;
}

export function isLogout(status: number) {
    if(status === 401){
        useToken().$reset()
        router.push("/login")
    }

}