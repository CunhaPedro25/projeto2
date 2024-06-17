import axios from 'axios';

const API_URL = 'https://json.geoapi.pt/cp';

export default {
    async fetchZipcodeDetails(zipcode) {
        try {
            const response = await axios.get(API_URL + `/${zipcode}`);
            if (response.data) {
                return response.data;
            }
        } catch (error) {
            console.error('Failed to fetch zipcode details:', error);
            return null;
        }
    },
};