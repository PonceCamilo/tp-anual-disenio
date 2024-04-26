import React from 'react'
import { Container } from 'react-bootstrap'
import Button from 'react-bootstrap/Button'


function HeaderApp() {
  return (
    <div className='text-center'style={{marginTop:'65px'}}>
      <h1 className="">Sistema para la Mejora del Acceso<br/>
        Alimentario en Contextos de<br/>
    Vulnerabilidad Socioeconómica</h1>
    <Container fluid className='d-flex bg-primary justify-content-around '>
        <Button  className='fs-4' variant="primary" style={{ minWidth: '200px' }}>
        Se voluntario
        </Button>
        <Button  className='fs-4' variant="primary" style={{ minWidth: '200px' }}>
        Doná
        </Button>
    </Container>
    </div>
  )
}

export default HeaderApp
