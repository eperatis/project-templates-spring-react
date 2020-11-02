import axios from 'axios';
import dispatcher from "../dispatcher/Dispatcher";
import * as actionConstants from '../dispatcher/CustomerActionConstants';

export const recordingCustomer = ({address,firstName,lastName,phoneNumber}) =>{
    axios.post('/customer/record',
        {
            address : address,
            firstName : firstName,
            lastName : lastName,
            phoneNumber : phoneNumber
        })
        .then(() => {
            fetchCustomers();
            dispatcher.dispatch({action: actionConstants.clearError});
        })
        .catch((err) => {
            dispatcher.dispatch({
                action : actionConstants.showError,
                payload: `${err.response.status}-${err.response.statusText}: ${err.response.data.message}`
            });
        });
}

export const fetchCustomers = () =>{
    axios.get('/customer/').then((resp)=>{
        dispatcher.dispatch({
            action : actionConstants.refresh,
            payload: resp.data
        });
    })
}