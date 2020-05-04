import React from 'react'

class CardTable extends React.Component {

    render() {
        return (
            <div className="card mb-3">
                <h3 className="card-header">{this.props.title}</h3>
                {this.props.children}
            </div>
        )
    }
}
export default CardTable
