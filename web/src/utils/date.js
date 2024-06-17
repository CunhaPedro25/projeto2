import {format} from "date-fns";

export default {
    getCurrentDate() {
        return format(new Date(), 'yyyy-MM-dd');
    },
    formatDate(dateString)  {
        const date = new Date(dateString);
        return format(date, 'dd/MM/yyyy');
    }
}