import { useEffect, useState } from 'react';
import './App.css';
import Product from './components/product/product';

const App = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8083/kss/api/product", {
      method: "GET"
    }).then(response => response.json()).then(data => {
      setProducts(data['_embedded']['products']);
    });
  }, []);

  return (
    <div className="App">
      <div className='products-container'>
        {products.map((product) => <Product productName={product['name']} productImage={product['image']} productPrice={product['price']} deleteUrl={product['_links']['product']['href']} />)}
      </div>
    </div>
  );
}

export default App;
