import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export default {
    async getAllConstructions() {
        try {
            const response = await axios.get(`${API_URL}/construction/get/all`);
            console.log(response.data)
            return response.data;
        } catch (error) {
            console.error('Error getting constructions:', error);
            throw error;
        }
    },
    async getClientConstructions(clientId) {
        try {
            console.log(`${API_URL}/construction/get/client/${clientId}`)
            const response = await axios.get(`${API_URL}/construction/get/client/${clientId}`);
            console.log(response.data)
            return response.data;
        } catch (error) {
            console.error('Error getting constructions:', error);
            throw error;
        }
    },
    async getProjectConstructions(projectId) {
        try {
            const response = await axios.get(`${API_URL}/construction/get/project/${projectId}`);
            return response.data;
        } catch (error) {
            console.error('Error getting constructions:', error);
            throw error;
        }
    },
};
