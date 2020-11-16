import React from 'react';
import {default as store} from "../store/SlotStore";
import {fetchMap} from "../action/SlotActions";
import Slot from "./Slot";

class Map extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            slots: [],
        };
        this._updateState = this._updateState.bind(this);
    }

    mapIndex;

    componentDidMount() {
        fetchMap()
        store.addChangeListener(this._updateState);
    }

    componentWillUnmount() {
        store.removeChangeListener(this._updateState);
    }

    _updateState() {
        this.setState({slots: store._map});
    }

    setupMap() {
        this.mapIndex = new Array(20).fill(0).map(() =>
            new Array(20).fill([1, 1, 0, ""]))

        for (let l = 0; l < this.state.slots.length; l++) {
            let dx = this.state.slots[l].endCoordinate.x - this.state.slots[l].startCoordinate.x + 1;
            let dy = this.state.slots[l].endCoordinate.y - this.state.slots[l].startCoordinate.y + 1;
            this.mapIndex[this.state.slots[l].startCoordinate.y - 1][this.state.slots[l].startCoordinate.x - 1] =
                [dx, dy, this.state.slots[l].slotStatus === "EMPTY" ? 1 : 2, this.state.slots[l].description];
        }
        for (let l = 0; l < this.state.slots.length; l++) {
            let dx = this.state.slots[l].endCoordinate.x - this.state.slots[l].startCoordinate.x + 1;
            let dy = this.state.slots[l].endCoordinate.y - this.state.slots[l].startCoordinate.y + 1;
            for (let i = 0; i < 20; i++) {
                for (let j = 0; j < 20; j++) {
                    if (this.state.slots[l].startCoordinate.y - 1 === i &&
                        this.state.slots[l].startCoordinate.x - 1 === j) {
                        if (dx > 1) {
                            for (let k = 1; k < dx; k++) {
                                this.mapIndex[i].splice(j + 1, 1);
                            }
                        }
                        if (dy > 1) {
                            for (let k = 1; k < dy; k++) {
                                this.mapIndex[i + k].splice(j + 1, 1);
                                for (let m = 1; m < dx; m++) {
                                    this.mapIndex[i + k].splice(j, 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    render() {
        this.setupMap()
        return (
            <div>
                <h1 className="text-center m-3">Térkép</h1>
                <table className="m-auto">
                    <tbody>
                    {this.mapIndex.map((row, i) => {
                        return (
                            <Slot key={i} row={row}/>
                        )
                    })}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default Map;
