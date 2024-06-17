import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export default {
    async getAllConstructions() {
        try {
            const response = await axios.get(`${API_URL}/construction/get/all`);
            return response.data;
        } catch (error) {
            console.error('Error getting constructions:', error);
            throw error;
        }
    },
    async getConstruction(id) {
        try {
            const response = await axios.get(`${API_URL}/construction/get/${id}`);
            return response.data;
        } catch (error) {
            console.error('Error getting constructions:', error);
            throw error;
        }
    },
    async getClientConstructions(clientId) {
        try {
            const response = await axios.get(`${API_URL}/construction/get/client/${clientId}`);
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
    async getEngineerConstructions(projectId) {
        try {
            const response = await axios.get(`${API_URL}/construction/get/engineer/${projectId}`);
            return response.data;
        } catch (error) {
            console.error('Error getting constructions:', error);
            throw error;
        }
    },

    async getTeamConstructions(teamId) {
        try {
            const response = await axios.get(`${API_URL}/constructionTeam/get/team/${teamId}`);
            return response.data;
        } catch (error) {
            console.error('Error getting constructions:', error);
            throw error;
        }
    },
    async getConstructionTeams(constructionId) {
        try {
            const response = await axios.get(`${API_URL}/constructionTeam/get/construction/${constructionId}`);
            return response.data;
        } catch (error) {
            console.error('Error getting construction team:', error);
            throw error;
        }
    },
    async getConstructionTeamByBoth(constructionId, teamId) {
        try {
            const response = await axios.get(`${API_URL}/constructionTeam/get/construction/${constructionId}/${teamId}`);
            return response.data;
        } catch (error) {
            console.error('Error getting construction team:', error);
            throw error;
        }
    },
    async getTeamCurrentConstruction(teamId) {
        try {
            const response = await axios.get(`${API_URL}/constructionTeam/get/working/team/${teamId}`);
            return response.data;
        } catch (error) {
            console.error('Error getting constructions:', error);
            throw error;
        }
    },
    async getWorkingConstructionTeams(constructionId) {
        try {
            let teams = await this.getConstructionTeams(constructionId);
            return teams.filter(team => team.endDate === null);
        } catch (error) {
            console.error('Error getting construction team:', error);
            throw error;
        }
    },

    async getTeamLeader(teamId) {
        try {
            const response = await axios.get(`${API_URL}/team/get/leader/${teamId}`);
            return response.data;
        } catch (error) {
            console.error('Error getting team leader:', error);
            throw error;
        }
    },

    async getProject(projectId) {
        try {
            const response = await axios.get(`${API_URL}/project/get/${projectId}`);
            return response.data;
        } catch (error) {
            console.error('Error getting project:', error);
            throw error;
        }
    },
    async getAllProjects() {
        try {
            const response = await axios.get(`${API_URL}/project/get/all`);
            response.data.sort((a, b) => {
                // Sort by requirementsState
                if (a.requirementsState === null && b.requirementsState !== null) {
                    return -1;
                }
                if (a.requirementsState !== null && b.requirementsState === null) {
                    return 1;
                }
                if (a.requirementsState === true && b.requirementsState === false) {
                    return -1;
                }
                if (a.requirementsState === false && b.requirementsState === true) {
                    return 1;
                }

                // If requirementsState is the same, sort by budgetState
                if (a.budgetState === null && b.budgetState !== null) {
                    return -1;
                }
                if (a.budgetState !== null && b.budgetState === null) {
                    return 1;
                }
                if (a.budgetState === true && b.budgetState === false) {
                    return -1;
                }
                if (a.budgetState === false && b.budgetState === true) {
                    return 1;
                }

                // If both states are the same, return 0 (no sorting)
                return 0;
            });
            return response.data;
        } catch (error) {
            console.error('Error getting projects:', error);
            throw error;
        }
    },
    async getClientProjects(id) {
        try {
            const response = await axios.get(`${API_URL}/project/get/client/${id}`);
            response.data.sort((a, b) => {
                // Sort by requirementsState
                if (a.requirementsState === null && b.requirementsState !== null) {
                    return -1;
                }
                if (a.requirementsState !== null && b.requirementsState === null) {
                    return 1;
                }
                if (a.requirementsState === true && b.requirementsState === false) {
                    return -1;
                }
                if (a.requirementsState === false && b.requirementsState === true) {
                    return 1;
                }

                // If requirementsState is the same, sort by budgetState
                if (a.budgetState === null && b.budgetState !== null) {
                    return -1;
                }
                if (a.budgetState !== null && b.budgetState === null) {
                    return 1;
                }
                if (a.budgetState === true && b.budgetState === false) {
                    return -1;
                }
                if (a.budgetState === false && b.budgetState === true) {
                    return 1;
                }

                // If both states are the same, return 0 (no sorting)
                return 0;
            });
            return response.data;
        } catch (error) {
            console.error('Error getting projects:', error);
            throw error;
        }
    },
    async getEngineerProjects(id) {
        try {
            const response = await axios.get(`${API_URL}/project/get/engineer/${id}`);
            response.data.sort((a, b) => {
                // Sort by requirementsState
                if (a.requirementsState === null && b.requirementsState !== null) {
                    return -1;
                }
                if (a.requirementsState !== null && b.requirementsState === null) {
                    return 1;
                }
                if (a.requirementsState === true && b.requirementsState === false) {
                    return -1;
                }
                if (a.requirementsState === false && b.requirementsState === true) {
                    return 1;
                }

                // If requirementsState is the same, sort by budgetState
                if (a.budgetState === null && b.budgetState !== null) {
                    return -1;
                }
                if (a.budgetState !== null && b.budgetState === null) {
                    return 1;
                }
                if (a.budgetState === true && b.budgetState === false) {
                    return -1;
                }
                if (a.budgetState === false && b.budgetState === true) {
                    return 1;
                }

                // If both states are the same, return 0 (no sorting)
                return 0;
            });
            return response.data;
        } catch (error) {
            console.error('Error getting projects:', error);
            throw error;
        }
    },

    async saveProject(project) {
        try {
            const response = await axios.post(`${API_URL}/project/add`, project);
            return response.data;
        } catch (error) {
            console.error('Error saving project:', error);
            throw error;
        }
    },
    async updateRequirementsState(projectId, state) {
        try {
            const response = await axios.put(`${API_URL}/project/update/requirementsState/${projectId}/${state}`);
            return response.data;
        } catch (error) {
            console.error('Error updating budget state:', error);
            throw error;
        }
    },
    async updateBudgetState(projectId, state) {
        try {
            const response = await axios.put(`${API_URL}/project/update/budgetState/${projectId}/${state}`);
            return response.data;
        } catch (error) {
            console.error('Error updating budget state:', error);
            throw error;
        }
    },

    async getAllConstructionTypes() {
        try {
            const response = await axios.get(`${API_URL}/constructionType/get/all`);
            return response.data;
        } catch (error) {
            console.error('Error getting construction types:', error);
            throw error;
        }
    },

    async getAllTeams() {
        try {
            const response = await axios.get(`${API_URL}/team/get/all`);
            return response.data;
        } catch (error) {
            console.error('Error getting teams:', error);
            throw error;
        }
    },

    async getFreeTeams() {
        try {
            const response = await axios.get(`${API_URL}/team/get/free`);
            return response.data;
        } catch (error) {
            console.error('Error getting teams:', error);
            throw error;
        }
    },

    async addTeamToConstruction(param) {
        try {
            const response = await axios.post(`${API_URL}/constructionTeam/add`, param);
            return response.data;
        } catch (error) {
            console.error('Error adding team to construction:', error);
            throw error;
        }
    },

    async finishConstruction(id) {
        try {
            const response = await axios.put(`${API_URL}/constructionTeam/finish/${id}`);
            return response.data;
        } catch (error) {
            console.error('Error finishing construction:', error);
            throw error;
        }
    },

    async getMaterial() {
        try {
            const response = await axios.get(`${API_URL}/material/get/all`);
            return response.data;
        } catch (error) {
            console.error('Error getting materials:', error);
            throw error;
        }
    },
    async requestStock(param) {
        try {
            const response = await axios.post(`${API_URL}/stockRequest/add`, param);
            return response.data;
        } catch (error) {
            console.error('Error requesting stock:', error);
            throw error;
        }
    },
    async getStockRequests(constructionId) {
        try {
            const response = await axios.get(`${API_URL}/stockRequest/get/construction/${constructionId}`);
            return response.data;
        } catch (error) {
            console.error('Error getting stock requests:', error);
            throw error;
        }
    },

    async getClientInvoices(id) {
        try {
            const response = await axios.get(`${API_URL}/invoices/get/client/${id}`);
            return response.data;
        } catch (error) {
            console.error('Error getting invoices:', error);
            throw error;
        }
    },
    async getUnpaidInvoices(id){
        try {
            const response = await this.getClientInvoices(id);
            return response.filter(invoice => invoice.paid === false);
        } catch (error) {
            console.error('Error getting invoices:', error);
            throw error;
        }
    },
    async payInvoice(id) {
        try {
            const response = await axios.put(`${API_URL}/invoices/pay/${id}`);
            return response.data;
        } catch (error) {
            console.error('Error paying invoice:', error);
            throw error;
        }
    }
};
