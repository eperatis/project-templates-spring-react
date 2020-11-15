import React from "react";
import * as actions from "../action/SlotActions";
import ErrorMessageWell from "./ErrorMessageWell";

class FreeSlotIntervalQueryForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            startDate: 0,
            endDate: 0
        };
        this.formOnChange = this.formOnChange.bind(this);
    }

    formOnChange(event) {
        const {name, value} = event.target;
        this.setState({[name]: value}, () => {
            if(this.state.startDate !== 0 && this.state.endDate !== 0){
                actions.fetchFreeSlotsBetweenInterval(this.state)
            }
        });
    }

    render() {
        return (
            <div>
                <h1 className="text-center m-3">Kiadható táborhelyek listája</h1>
                <ErrorMessageWell/>
                <div className="row">
                    <div className="col text-center">
                        <label htmlFor={"startDate"}>Kezdés dátuma</label>
                        <br/>
                        <input type={"date"} id={"startDate"} name={"startDate"} value={this.state.startDate}
                               onChange={this.formOnChange}/>
                    </div>
                    <div className="col text-center">
                        <label htmlFor={"endDate"}>Befejezés dátuma</label>
                        <br/>
                        <input type={"date"} id={"endDate"} name={"endDate"} value={this.state.endDate}
                               onChange={this.formOnChange}/>
                    </div>
                </div>
            </div>
        );
    }
}

export default FreeSlotIntervalQueryForm;
