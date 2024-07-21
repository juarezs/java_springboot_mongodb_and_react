import '@testing-library/jest-dom';
import { fireEvent, render } from '@testing-library/react';
import { describe, expect, test, vi } from 'vitest';
import ProductCard from './ProductCard';
import TProduct from '@/models/TProduct';

describe('UseCard', () => {

  test('should render component and delete', async () => {
    const product: TProduct = {
      id: '123',
      name: 'Product 1',
      description: 'Description of production 1',
      image: 'https://test.com/img.png',
      price: 1256.9,
    };

    const onDelete = vi.fn();

    const { getByTestId, getByText } = render(<ProductCard product={product} onDelete={onDelete} />)

    expect(getByTestId('product-card')).toBeInTheDocument();
    expect(getByText('Product 1 | R$ 1.256,90')).toBeInTheDocument();

    const deleteButton: HTMLButtonElement = getByTestId("product-delete") as HTMLButtonElement;
    fireEvent.click(deleteButton);
    await vi.waitFor(
      () => {
        expect(onDelete).toHaveBeenCalledWith(product.id);
      }
    );
  });

});
