import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import FoodAnimation from './FoodAnimation'
import './css/CustomContainer.css'
function BodyApp() {
  return (
    <Container className='mt-5 custom-container' fluid>
      <Row className="justify-content-center align-items-center">
        <Col md={6} className="d-flex justify-content-center flex-column align-items-center">
        <FoodAnimation/>
        </Col>
        <Col className="d-flex justify-content-center align-items-center" md={6}>
          <article className='text-center align-items-center' style={{maxWidth:'350px'}}>
            <h2>¿Qué es Heladeras ...?</h2>
            <p>Heladeras ... es una organización sin fines de lucro que busca mejorar el acceso alimentario en contextos de vulnerabilidad socioeconómica. Para ello, se instalan heladeras comunitarias en distintos puntos de la ciudad, donde los vecinos pueden dejar y retirar alimentos de manera gratuita.</p>
          </article>
        </Col>
      </Row>
    </Container>
  );
}
export default BodyApp
