import { createRouter, createWebHistory } from 'vue-router'
import NotFound from "@/views/error/NotFound.vue";
import Login from "@/views/auth/Login.vue";
import SignIn from "@/views/auth/SignIn.vue";
import SearchId from "@/views/auth/SearchId.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/signin',
      name: 'signIn',
      component: SignIn
    },
    {
      path: '/searchid',
      name: 'searchid',
      component: SearchId
    },

    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   // component: () => import('../views/AboutView.vue')
    // },
    {
      path: '/:pathMatch(.*)*',
      name: 'NotFound',
      component: NotFound,
      props: true,
    }
  ]
})

export default router
