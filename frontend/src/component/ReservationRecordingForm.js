import React from "react";
import {Link} from "react-router-dom";
import ErrorMessageWell from "./ErrorMessageWell";
import * as actions from "../action/ReservationAction";

class ReservationRecordingForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            address: "",
            firstName: "",
            lastName: "",
            phoneNumber: "",
            electricity: false,
            end: this.props.location.state.end,
            paymentStatus: "NOT_PAID",
            slotId: this.props.location.state.id,
            start: this.props.location.state.start,
        };
        this.formOnChange = this.formOnChange.bind(this);
    }

    formOnChange(event) {
        const {name, value} = event.target;
        if (name === "electricity") {
            this.setState({[name]: !this.state.electricity})
        } else {
            this.setState({[name]: value});
        }
    }

    render() {
        return (
            <div>
                <ErrorMessageWell/>
                <h1 className="text-center m-3">Rendelés felvétele</h1>
                <div className="form-row">
                    <div className="form-group col-md-6">
                        <label htmlFor={"lastName"}>Vezetéknév</label>
                        <input type={"string"} className="form-control" id={"lastName"} name={"lastName"}
                               defaultValue={this.state.lastName} onChange={this.formOnChange}/>
                    </div>
                    <div className="form-group col-md-6">
                        <label htmlFor={"firstName"}>Keresztnév</label>
                        <input type={"string"} className="form-control" id={"firstName"} name={"firstName"}
                               defaultValue={this.state.firstName} onChange={this.formOnChange}/>
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor={"address"}>Cím</label>
                    <input type={"text"} className="form-control" id={"address"} name={"address"}
                           value={this.state.address} onChange={this.formOnChange}/>
                </div>
                <div className="form-group col-md-3 pl-0">
                    <label htmlFor={"phoneNumber"}>Telefonszám</label>
                    <input type={"string"} className="form-control" id={"phoneNumber"} name={"phoneNumber"}
                           defaultValue={this.state.phoneNumber} onChange={this.formOnChange}/>
                </div>
                <div className="form-group">
                    <div className="form-check">
                        <input className="form-check-input" type={"checkbox"} name={"electricity"}
                               checked={this.state.electricity} onChange={this.formOnChange}/>
                        <label className="form-check-label" htmlFor={"electricity"}>Áram vásárás</label>
                    </div>
                </div>
                <Link className="btn btn-outline-dark mr-3" to={{pathname: "/"}} onClick={() => {
                    actions.recordingReservation(this.state)
                }}>Submit
                </Link>
            </div>
        );
    }
}

export default ReservationRecordingForm;
