import axios from "axios";
import {type User, useToken} from "@/store/member";

export interface Login {
    userId: string,
    userPwd: string,
}

export async function isLoginChk(tokenType: string, token: string) {
    const result = await axios.get(import.meta.env.VITE_API_URL+"/user/islogin", { headers: {
            Authorization: tokenType + ' ' + token
        }})
        .then(res => res.data)
        .catch(err => err.response);

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