import {defineStore} from "pinia";
import {isLoginChk} from "@/api/memberApi";
export interface User {
    userId: string;
    userPwd: string;
    userPwd2: string;
    email: string;
}

export interface UserToken {
    userId?: string | undefined
    tokenType?: string | undefined
    token?: string | undefined
    expireIn?: number | undefined
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
        return {}
    },
    getters: {
      getInfo: state => ({
          userId: state.userId,
          token: state.token,
          tokenType: state.tokenType,
          expireIn: state.expireIn,
      })
    },
    actions: {
        login(value: UserToken) {
            this.userId = value.userId
            this.token = value.token
            this.tokenType = value.tokenType
            this.expireIn = value.expireIn
        },
        async isLogin() {
            return await isLoginChk(this.tokenType!!, this.token!!)
                .then(res => res.status === 200 ? true : false)
                .catch(err => false)
        }
    }
})
