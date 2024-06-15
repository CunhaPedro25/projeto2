import axios from 'axios';

const API_URL = 'http://localhost:8080/api/users';

export default {
    async login(email, password) {
        try {
            const response = await axios.post(`${API_URL}/login`, null, {
                params: {
                    email,
                    password
                }
            });
            return response.data;
        } catch (error) {
            console.error('Error during login:', error);
            throw error; // Throw error to handle it in caller function
        }
    },
    async updateUser(user) {
        try {
            const response = await axios.post(`${API_URL}/update`, user);
            if (response.data) {
                return response.data;
            }
        } catch (error) {
            console.error('Failed to update user:', error);
            return null;
        }
    },
    async getUserById(userId) {
        const response = await axios.get(`${API_URL}/get/${userId}`);
        return response.data;
    },
};
