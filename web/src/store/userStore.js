import {defineStore} from 'pinia';
import auth from '@/services/auth';
import Cookies from 'js-cookie';

export const useUserStore = defineStore('user', {
    state: () => {
        return {
            id: 0,
            name: '',
            email: '',
            phone: '',
            address: '',
            door: 0,
            zipcode: '',
            userType: 0,
            team: 0,
            active: true,
        }
    },
    actions: {
        setUser(user) {
            this.id = user.id;
            this.name = user.name;
            this.email = user.email;
            this.phone = user.phone;
            this.address = user.address;
            this.door = user.door;
            this.zipcode = user.zipcode;
            this.userType = user.userType;
            this.team = user.team;
            this.active = user.active;
        },
        async login(credentials) {
            try {
                const user = await auth.login(credentials.email, credentials.password);
                this.setUser(user);
                this.setCookie();
            } catch (error) {
                console.error('Login failed:', error);
                throw error;
            }
        },
        async logout() {
            this.$reset();
            Cookies.remove("user_id");
        },
        setCookie(){
            Cookies.set("user_id", this.id);
        },
        async loadUserFromCookie() {
            const userId = Cookies.get("user_id");
            if (userId) {
                // Fetch user data using the user ID from the cookie
                // Here, you should implement a method to fetch user data from your backend
                const user = await this.fetchUserDataById(userId);
                if (user) {
                    this.setUser(user);
                }
            }
        },
        async fetchUserDataById(userId) {
            // Replace this with actual API call to fetch user data by ID
            try {
                return await auth.getUserById(userId);
            } catch (error) {
                console.error('Failed to fetch user data:', error);
                return null;
            }
        },
    },
});