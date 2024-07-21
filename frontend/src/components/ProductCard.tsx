import TProduct from "../models/TProduct";
import { Button } from "./ui/button";

export default function ProductCard({ product, onDelete } : { product: TProduct, onDelete: (id: string) => {}}) {
  const formattedNumber = new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL',
  }).format(product.price);

  return <div key={product.id} data-testid="product-card">
    <img src={product.image} />
    <div className="text-center">
      <span>{product.name} | {formattedNumber}</span>
    </div>
    <div className="text-center">
      <Button type="button" variant="destructive" data-testid="product-delete" onClick={() => onDelete(product.id)}>Delete</Button>
      </div>
  </div>;
}
