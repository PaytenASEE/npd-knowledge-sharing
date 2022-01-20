import './product.scss';

const Product = ({productName, productImage, productPrice, deleteUrl}) => {
    return (
        <div className="product">
            <h3>{productName}</h3>
            <img src={productImage} />
            <p>${productPrice}</p>
            <button onClick={() => {
                fetch(deleteUrl, {
                    method: "DELETE"
                }).then(response => response.json()).then(data => {
                    console.log(data);
                });
            }}>Delete</button>
        </div>
    );
}

export default Product;