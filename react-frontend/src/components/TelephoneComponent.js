import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import { Link } from 'react-router-dom';


class TelephoneComponent extends React.Component {

    constructor(props){
        super(props);
        this.state = {
            telephones:[],
            userId:0
        };
        this.remove = this.remove.bind(this);
    }

    componentDidMount(){
        const userId = this.props.match.params.id;
        this.setState({userId: userId});
        this.setState({isLoading: true});
        
        fetch('/api/user/telephones/' + userId)
            .then(response => response.json())
            .then(data => this.setState({telephones: data, isLoading: false}));
    }
    

    async remove(id) {
        await fetch(`/api/user/telephone/${this.state.userId}/${id}`, {
          method: 'DELETE',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          }
        }).then(() => {
          let updatedTelephone = [...this.state.telephones].filter(i => i.id !== id);
          this.setState({telephones: updatedTelephone});
        });
    }

    render (){
        const {telephones, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        return (
            <div className="container m-5 mx-auto">
                <Button size="sm" color="secondary" tag={Link} to={"/users"}>Regresar</Button>
                <Button size="sm" color="success" tag={Link} to={"/telephone/" + this.state.userId + "/add"} className="float-right">Crear Teléfono</Button>
                <div className="text-center">
                    <h1>Lista de Teléfonos</h1>
                    <Table className="mt-4">
                        <thead>
                            <tr>
                                <th>Número</th>
                                <th>Gestión</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            {
                                this.state.telephones.map(
                                    telephone =>
                                    <tr key = {telephone.id}>
                                        <td>{telephone.number}</td>
                                        <td>
                                            <ButtonGroup>
                                                <Button size="sm" color="primary" tag={Link} to={"/telephone/" + this.state.userId + "/" + telephone.id}>Editar</Button>
                                                <Button size="sm" color="danger" onClick={() => this.remove(telephone.id)}>Eliminar</Button>
                                            </ButtonGroup>
                                        </td>
                                    </tr>
                                )
                            }
                            
                        </tbody>
                    </Table>
                </div>
            </div>
        )
    }
}

export default TelephoneComponent;