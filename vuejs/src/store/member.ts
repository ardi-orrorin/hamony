import {defineStore} from "pinia";
export interface User {
    userId: string;
    userPwd: string;
    userPwd2: string;
    email: string;
}

export interface UserToken {
    userId: string
    tokenType?: string
    token: string
    expireIn: number
}
export const useSignIn = defineStore('signIn', {
    state: (): User => {
        return {
            userId: '',
            userPwd: '',
            userPwd2: '',
            email: ''
        }
    }
})

export const useToken = defineStore('token', {
    state: (): UserToken => {
        return {
            userId: '',
            token: '',
            expireIn: 0,
        }
    },
    actions: {
        login(value: UserToken) {
            this.userId = value.userId
            this.token = value.token
            this.expireIn = value.expireIn
        }
    }

})
