import { createRouter, createMemoryHistory } from 'vue-router';
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
        component: Dashboard,
        meta: { requiresAuth: true },
        children: [
            {
                path: 'home',
                component: () => import(/* webpackChunkName: "about" */ './views/dashboards/Home.vue'),
                meta: { requiresAuth: true }
            },
            {
                path: 'project',
                component: () => import(/* webpackChunkName: "about" */ './views/dashboards/Home.vue'),
                meta: { requiresAuth: true }
            },
            {
                path: 'budget',
                component: () => import(/* webpackChunkName: "about" */ './views/dashboards/Home.vue'),
                meta: { requiresAuth: true }
            },
            {
                path: 'construction',
                component: () => import(/* webpackChunkName: "about" */ './views/dashboards/Home.vue'),
                meta: { requiresAuth: true }
            },
        ]
    },
    { path: '/login', component: Login },
];

const router = createRouter({
    history: createMemoryHistory(),
    routes,
});

router.beforeEach(async (to, from, next) => {
    const userIdFromCookie = Cookies.get('user_id');
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
