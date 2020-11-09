import React from "react";
import {default as store} from '../store/CustomerStore';
import CustomerItemList from "./CustomerItemList";

class CustomerList extends React.Component{

    constructor(props) {
        super(props);
        this.state = {customers : []};
        this._updateState = this._updateState.bind(this);
    }

    componentDidMount() {
        store.addChangeListener(this._updateState);
    }


    componentWillUnmount() {
        store.removeChangeListener(this._updateState);
    }

    _updateState(){
        this.setState({customers : store._customers});
    }

    render() {
        return(
            <div>
                {this.state.customers.map(({address,firstName,lastName,phoneNumber}, index) =>{
                    return(
                      <CustomerItemList key={index} address={address} firstName={firstName} lastName={lastName} phoneNumber={phoneNumber} />
                    );
                })}
            </div>
        );
    }
}

export default CustomerList;