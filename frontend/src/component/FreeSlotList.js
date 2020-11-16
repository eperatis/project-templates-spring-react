import React from "react";
import FreeSlotListItem from "./FreeSlotListItem";
import FreeSlotIntervalQueryForm from "./FreeSlotIntervalQueryForm";
import store from "../store/SlotStore";

class FreeSlotList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {freeSlots: []};
        this._updateState = this._updateState.bind(this);
        this.queryParams = React.createRef();
    }

    componentDidMount() {
        store.addChangeListener(this._updateState);
    }

    componentWillUnmount() {
        store.removeChangeListener(this._updateState);
    }

    _updateState() {
        this.setState({freeSlots: store._freeSlots});
    }

    render() {
        return (
            <div>
                <FreeSlotIntervalQueryForm ref={this.queryParams}/>
                {this.state.freeSlots.map(({id, name, description, startCoordinate, endCoordinate}, index) => {
                    return (
                        <FreeSlotListItem key={index} id={id} name={name} description={description}
                                          startCoordinate={startCoordinate} endCoordinate={endCoordinate}
                                          startDate={this.queryParams.current.state.startDate}
                                          endDate={this.queryParams.current.state.endDate}/>
                    );
                })}
            </div>
        );
    }
}

export default FreeSlotList;
