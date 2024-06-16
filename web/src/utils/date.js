import {format} from "date-fns";

export default {
    formatDate(dateString)  {
        const date = new Date(dateString);
        return format(date, 'dd/MM/yyyy HH:mm');
    }
}