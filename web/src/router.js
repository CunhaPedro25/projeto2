import {createRouter, createWebHistory} from 'vue-router';
import { useUserStore } from '@/store/userStore';
import Dashboard from '@/views/Dashboard.vue';
import Login from '@/views/Login.vue';
import Cookies from "js-cookie";

const routes = [
    {
        path: '/',
        redirect: () => {
            const userIdFromCookie = Cookies.get('user_id');
            return userIdFromCookie ? '/dashboard/home' : '/login';
        }},
    {
        path: '/dashboard',
        name: 'Dashboard',
        component: Dashboard,
        meta: { requiresAuth: true },
        children: [
            {
                path: 'home',
                name: 'Home',
                component: () => import(/* webpackChunkName: "about" */ './views/dashboards/Home.vue'),
                meta: { requiresAuth: true }
            },
            {
                path: 'project',
                name: 'Project',
                component: () => import(/* webpackChunkName: "about" */ './views/dashboards/Home.vue'),
                meta: { requiresAuth: true }
            },
            {
                path: 'budget',
                name: 'Budget',
                component: () => import(/* webpackChunkName: "about" */ './views/dashboards/Home.vue'),
                meta: { requiresAuth: true }
            },
            {
                path: 'construction',
                name: 'Construction',
                component: () => import(/* webpackChunkName: "about" */ './views/dashboards/Construction.vue'),
                meta: { requiresAuth: true }
            },
        ]
    },
    { path: '/login', component: Login },
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});

router.beforeEach(async (to, from, next) => {
    const userIdFromCookie = Cookies.get('user_id');
    if(!userIdFromCookie && to.name !== 'login') {
        next('/login');
        return;
    }
    let userStore = null;

    if (userIdFromCookie) {
        userStore = useUserStore();
        await userStore.loadUserFromCookie();
    }

    if (to.matched.some((record) => record.meta.requiresAuth) && !userIdFromCookie) {
        next('/login');
    }

    next();
});

export default router;
