import { createRouter, createWebHistory } from 'vue-router'
import NotFound from "@/views/error/NotFound.vue";
import Login from "@/views/auth/Login.vue";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
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
