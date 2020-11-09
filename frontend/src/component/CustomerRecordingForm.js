import React from "react";
import ErrorMessageWell from "./ErrorMessageWell";
import * as actions from "../action/CustomerActions";

class CustomerRecordingForm extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            address: "",
            firstName: "",
            lastName: "",
            phoneNumber: ""
        };
        this.formOnChange = this.formOnChange.bind(this);
    }

    formOnChange(event){
        const {name,value} = event.target;
        this.setState({[name] : value});
    }

    render() {
        return (
            <div>
                <ErrorMessageWell/>
                <label htmlFor={"address"}>Address</label>
                <input type={"string"} id={"address"} name={"address"} value={this.state.address}
                       onChange={this.formOnChange}/>
                <br/>
                <label htmlFor={"firstName"}>Firstname</label>
                <input type={"string"} id={"firstName"} name={"firstName"} value={this.state.firstName} onChange={this.formOnChange}/>
                <br/>
                <label htmlFor={"lastName"}>Lastname</label>
                <input type={"string"} id={"lastName"} name={"lastName"} value={this.state.lastName} onChange={this.formOnChange}/>
                <br/>
                <label htmlFor={"phoneNumber"}>Phone number</label>
                <input type={"string"} id={"phoneNumber"} name={"phoneNumber"} value={this.state.phoneNumber} onChange={this.formOnChange}/>
                <br/>
                <button onClick={() => actions.recordingCustomer(this.state)}>Submit</button>
            </div>
        );
    }
}

export default CustomerRecordingForm;