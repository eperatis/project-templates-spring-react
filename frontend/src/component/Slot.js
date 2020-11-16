import React from 'react';

function Slot({row, i}) {
    return (
        <tr key={i}>
            {row.map((column, i) => {
                const color = row[i][2] > 0 ? row[i][2] === 1 ? "bg-success" : "bg-danger" : "bg-light"
                const cellStyle = {
                    width: "40px",
                    height: "40px",
                    border: "1px solid black",
                    textAlign: "center",
                    verticalAlign: "middle"
                }
                return (<td style={cellStyle} className={String(color)} rowSpan={row[i][1]} colSpan={row[i][0]}
                            key={i}>{row[i][3]}</td>)
            })}
        </tr>
    );
}

export default Slot;
