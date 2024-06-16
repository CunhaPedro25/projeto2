const userTypeConfig = {
    client: [
        { name: "Home", page: "home", icon: "home"},
        { name: "Projects", page: "project", icon: "description"},
        { name: "Budgets", page: "budget", icon: "request_page"},
        { name: "Constructions", page: "constructions", icon: "house_siding"}
    ],
    worker: [
        { name: "Home", page: "construction", icon: "home"},
        { name: "Constructions", page: "constructions", icon: "house_siding"},
    ],
};


export function createItems(userType) {
    return userTypeConfig[userType];
}
