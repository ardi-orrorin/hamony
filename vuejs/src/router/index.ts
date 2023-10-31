import {createRouter, createWebHistory} from 'vue-router'
import NotFound from "@/views/error/NotFound.vue";
import Login from "@/views/auth/Login.vue";
import SignIn from "@/views/auth/SignIn.vue";
import SearchId from "@/views/auth/SearchId.vue";
import Write from "@/views/diary/Write.vue";
import Root from "@/views/root/Root.vue";
import Recent from "@/views/diary/Recent.vue";
import {useToken} from "@/store/member";
import Profile from "@/views/profile/Profile.vue";
import {getDiary, profileList} from "@/api/diaryApi";
import Test from "@/components/Test.vue";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'root',
      component: Root,
      meta: {requiresAuth: true},
      children: [
        {
          path: '/',
          name: 'rootRecent',
          component: Recent,
        },
        {
          path: 'recent',
          name: 'recent',
          component: Recent,
        },
        {
          path: 'searchid',
          name: 'searchId',
          component: SearchId,
        },
        {
          path: 'write',
          name: 'write',
          component: Write,
        },
        {
          path: 'upload',
          name: 'upload',
          component: Test,
        },
        {
          path: 'read/:id',
          name: 'read',
          props: true,
          component: () => import("@/views/diary/Read.vue"),
          beforeEnter: (to) => {
            getDiary(to.params.id.toString())
          }
        },
        {
          path: 'profile',
          name: 'profile',
          component: Profile,
          children: [
            {
              path: 'list',
              name: 'profileList',
              component: ()=> import("@/views/profile/ProfileList.vue"),
              meta: {tab: 'list'},
              beforeEnter: ()=>{profileList('list')}
            },
            {
              path: 'favorite',
              name: 'profileFavorite',
              component: ()=> import("@/views/profile/ProfileList.vue"),
              meta: {tab: 'fav'},
              beforeEnter: ()=>{profileList('fav')}
            },
            {
              path: 'order',
              name: 'profileOrder',
              component: ()=> import("@/views/profile/ProfileList.vue"),
              meta: {tab: 'order'},
              beforeEnter: ()=>{profileList('order')}
            },
          ]
        },
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
      meta: { requiresAuth : false}
    },
    {
      path: '/signin',
      name: 'signIn',
      component: SignIn,
      meta: { requiresAuth : false}
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'NotFound',
      component: NotFound,
      props: true,
      meta: { requiresAuth : false}
    }
  ]
})


router.beforeEach((to, from) => {
  if(to.meta.requiresAuth && !useToken().isLogin()){
    return {
      path: '/login',
      query: { redirect: to.fullPath} // 다시 올 수 있도록 방문한 위치 저장
    }
  }
})


export default router
