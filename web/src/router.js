import {createRouter, createWebHistory} from 'vue-router';
import { useUserStore } from '@/store/userStore';
import Dashboard from '@/views/Dashboard.vue';
import Login from '@/views/Login.vue';
import Register from '@/views/Register.vue';
import Cookies from "js-cookie";
import {createItems} from "./utils/sidebarItemFactory.js";
import path from 'path-browserify';


const routes = [
    {
        path: '/',
        redirect: () => {
            const userIdFromCookie = Cookies.get('user_id');
            const userType = Cookies.get('user_type');
            return userIdFromCookie ? path.join('/dashboard/', createItems(userType)[0].page) : '/login';
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
                path: 'projects',
                name: 'Projects',
                component: () => import(/* webpackChunkName: "about" */ './views/dashboards/Projects.vue'),
                meta: { requiresAuth: true }
            },
            {
                path: 'budgets',
                name: 'Budgets',
                component: () => import(/* webpackChunkName: "about" */ './views/dashboards/Budgets.vue'),
                meta: { requiresAuth: true }
            },
            {
                path: 'constructions',
                name: 'Constructions',
                component: () => import(/* webpackChunkName: "about" */ './views/dashboards/Constructions.vue'),
                meta: { requiresAuth: true }
            },
            {
                path: 'construction',
                name: 'Current Construction',
                component: () => import(/* webpackChunkName: "about" */ './views/dashboards/Construction.vue'),
                meta: { requiresAuth: true },
                children: [
                    {
                        path: ':id',
                        name: 'Construction',
                        component: () => import(/* webpackChunkName: "about" */ './views/dashboards/Construction.vue'),
                        meta: { requiresAuth: true }
                    },
                ]
            },
            {
                path: 'invoices',
                name: 'Invoices',
                component: () => import(/* webpackChunkName: "about" */ './views/dashboards/Home.vue'),
                meta: { requiresAuth: true }
            },
        ]
    },
    { path: '/login', component: Login },
    { path: '/register', component: Register },
    { path: '/logout',
        redirect: () => {
            Cookies.remove('user_id');
            return '/login';
        }
    },
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});

router.beforeEach(async (to, from, next) => {
    if(to.fullPath === '/logout') return;
    const userIdFromCookie = Cookies.get('user_id');

    let userStore = null;

    if (userIdFromCookie) {
        userStore = useUserStore();
        await userStore.loadUserFromCookie();
    }

    if (to.matched.some(record => record.meta.requiresAuth) && !userIdFromCookie) {
        next('/login');
    } else {
        next();
    }
});

export default router;
