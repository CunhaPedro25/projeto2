import { createRouter, createMemoryHistory } from 'vue-router';
import { useUserStore } from '@/store/userStore';
import Dashboard from '@/views/Dashboard.vue';
import Login from '@/views/Login.vue';
import Cookies from "js-cookie";

const routes = [
    { path: '/', component: Dashboard, meta: { requiresAuth: true } },
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
        userStore = useUserStore()
        await userStore.loadUserFromCookie();
    }

    if (to.matched.some((record) => record.meta.requiresAuth) && !userIdFromCookie) {
        next('/login');
    }

    next();
});

export default router;
