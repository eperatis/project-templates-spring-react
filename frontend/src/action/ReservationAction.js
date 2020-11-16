import axios from 'axios';
import dispatcher from "../dispatcher/Dispatcher";
import * as actionConstants from '../dispatcher/ReservationActionConstants';
import * as slotActions from "./SlotActions";

export const recordingReservation = ({address, firstName, lastName, phoneNumber, electricity, end, slotId, start},
                                     history) => {
    axios.post('/reservation/record',
        {
            customer: {
                address: address,
                firstName: firstName,
                lastName: lastName,
                phoneNumber: phoneNumber
            },
            electricity: electricity,
            end: end,
            slotId: slotId,
            start: start
        })
        .then(() => {
            slotActions.fetchMap();
            dispatcher.dispatch({action: actionConstants.clearError});
            history.push("/");
        })
        .catch((err) => {
            dispatcher.dispatch({
                action: actionConstants.showError,
                payload: `${err.response.status}-${err.response.statusText}: ${err.response.data.message}`
            });
        });
}
