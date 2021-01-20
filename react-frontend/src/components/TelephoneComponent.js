import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
// import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';


class TelephoneComponent extends React.Component {

    constructor(props){
        super(props);
        this.state = {
            telephones:[]
        };
        this.remove = this.remove.bind(this);
    }

    componentDidMount(){
        // UserService.getUsers().then((response) => {
        //     this.setState({ users: response.data})
            
        // });
        this.setState({isLoading: true});

        fetch('api/user/telephone')
            .then(response => response.json())
            .then(data => this.setState({telephones: data, isLoading: false}));
    }
    

    async remove(id) {
        await fetch(`/api/user/${id}`, {
          method: 'DELETE',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          }
        }).then(() => {
          let updatedUser = [...this.state.telephones].filter(i => i.id !== id);
          this.setState({telephones: updatedUser});
        });
    }

    render (){
        const {telephones, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        return (
            <div className="text-center">
                
                <Button size="sm" color="primary" tag={Link} to={"/telephones/add"}>Crear Telefono</Button>
                <h1>Lista Telefonos</h1>
                <Table className="mt-4">
                    <thead>
                        <tr>
                            <th>Numero</th>
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
                                            <Button size="sm" color="primary" tag={Link} to={"/telephones/" + telephone.id}>Editar</Button>
                                            <Button size="sm" color="danger" onClick={() => this.remove(telephone.id)}>Eliminar</Button>
                                            
                                        </ButtonGroup>
                                    </td>
                                </tr>
                            )
                        }
                        
                    </tbody>
                </Table>
                
            </div>
        )
    }
}

export default TelephoneComponent;