import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import './Login.css';
import { useNavigate } from "react-router-dom";

const Login = (props) => {
    const [validated, setValidated] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            fetch('YOUR_URL')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    console.log(data);
                    alert('Login Successful')
                })
                .catch(error => {
                    console.error('There has been a problem with your fetch operation:', error);
                });
        }

        setValidated(true);
    };

    return (
        <div className="glass-container">
            <div className="glass-form-container">
                <h2>Login</h2>
                <Form noValidate validated={validated} onSubmit={handleSubmit}>
                    <Form.Group className="mb-3" controlId="emailAddress">
                        <Form.Label >Email address</Form.Label>
                        <Form.Control required type="email" placeholder="Enter email" />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="password">
                        <Form.Label >Password</Form.Label>
                        <Form.Control required type="password" placeholder="Password" />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicCheckbox">
                        <p >Not a registered user!
                            <span
                                onClick={() => { navigate("/signup"); }}
                                style={{ cursor: 'pointer', color: 'blue' }}> SignUp</span>
                        </p>
                    </Form.Group>
                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
            </div>
        </div >
    );
}

export default Login;