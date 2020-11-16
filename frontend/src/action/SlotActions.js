import axios from 'axios';
import dispatcher from '../dispatcher/Dispatcher';
import * as actionConstants from '../dispatcher/SlotActionConstants'

export const fetchMap = () => {
    axios.get('/slot/status').then((resp) => {
        dispatcher.dispatch({
            action: actionConstants.refreshMap,
            payload: resp.data
        });
    })
}

export const fetchFreeSlotsBetweenInterval = ({startDate, endDate}) => {
    axios.post('/slot',
        {
            start: startDate,
            end: endDate
        }).then((resp) => {
        dispatcher.dispatch({
            action: actionConstants.refreshSlots,
            payload: resp.data
        })
        dispatcher.dispatch({action: actionConstants.clearError})
    })
        .catch((err) => {
            dispatcher.dispatch({
                action: actionConstants.showError,
                payload: `${err.response.status}-${err.response.statusText}: ${err.response.data.message}`
            });
            dispatcher.dispatch({
                action: actionConstants.refreshSlots,
                payload: []
            })
        });
}
