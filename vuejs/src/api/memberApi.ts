import axios from "axios";
import {type User, useToken} from "@/store/member";

export interface Login {
    userId: string,
    userPwd: string,
}

export async function login(data: Login) {

    const result = await axios.post(import.meta.env.VITE_API_URL+"/user/login", data)
        .then(res => res.data)
        .catch(err => err.response);

    console.log(result);

    if(result.status === 200)
        useToken().login(result);

    return result;
}

export async function signIn(data: User) {
    const result = await axios.post(import.meta.env.VITE_API_URL+"/user/signin", data)
        .then(res => res.data)
        .catch(err => err.response)

    console.log(result);

    return result;
}