import React from 'react';
import {Link} from "react-router-dom";

function FreeSlotListItem({id, description, startCoordinate, endCoordinate, startDate, endDate}) {
    return (
        <div className="card border-dark mt-3 mb-3">
            <div className="card-body text-dark">
                <div className="row">
                    <h5 className="card-title col">{id}</h5>
                    <p className="card-text col text-right">{endCoordinate.x - startCoordinate.x + 1} x {endCoordinate.y - startCoordinate.y + 1}</p>
                </div>
                <div className="row">
                    <p className="card-text col">{description}</p>
                    {/*<button type="button" className="btn btn-outline-dark mr-3" onClick={*/}
                    <Link className="btn btn-outline-dark mr-3" to={{
                        pathname: "/reservation/record",
                        state: {
                            id: id,
                            start: startDate,
                            end: endDate
                        }
                    }}
                    >Lefoglal</Link>
                    {/*}>Lefoglal</button>*/}
                </div>
            </div>
        </div>
    );
}

export default FreeSlotListItem;
