const userTypeConfig = {
    client: [
        {name: "Home", page: "home", icon: "home"},
        {name: "Projects", page: "projects", icon: "description"},
        {name: "Budgets", page: "budgets", icon: "request_page"},
        {name: "Constructions", page: "constructions", icon: "house_siding"},
        {name: "Invoices", page: "invoices", icon: "receipt_long"}
    ],
    worker: [
        {name: "Home", page: "construction", icon: "home"},
        {name: "Constructions", page: "constructions", icon: "house_siding"},
        {name: "Stock Requests", page: "requests", icon: "inventory"},
    ],
    engineer: [
        {name: "Home", page: "home", icon: "home"},
        {name: "Projects", page: "projects", icon: "description"},
        {name: "Constructions", page: "constructions", icon: "house_siding"},
    ],
};

export function createItems(userType) {
    return userTypeConfig[userType];
}
