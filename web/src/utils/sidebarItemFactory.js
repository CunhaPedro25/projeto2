const userTypeConfig = {
    client: [
        { name: "Home", page: "home", icon: "home"},
        { name: "Projects", page: "project", icon: "home"},
        { name: "Budgets", page: "budget", icon: "home"},
        { name: "Constructions", page: "construction", icon: "house_siding"}
    ],
    worker: [
        { name: "Home", page: "home", icon: "home"},
        { name: "Team", page: "team", icon: "home"},
        { name: "Constructions", page: "construction", icon: "house_siding"},
    ],
};


export function createItems(userType) {
    return userTypeConfig[userType];
}