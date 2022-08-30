import React from 'react';
import CoinPanel from './CoinPanel';

function PiggyBank() {
    return (
        <div className="row">
            <CoinPanel className="col" amount={0.25} maxClicks={10} />
            <CoinPanel className="col" amount={0.10} maxClicks={10} />
            <CoinPanel className="col" amount={0.05} maxClicks={10} />
            <CoinPanel className="col" amount={0.01} maxClicks={10} />
        </div>
    );
}

export default PiggyBank;