import Home from "./Home";
import '@testing-library/jest-dom';
import { render } from '@testing-library/react';
import { describe, expect, test } from 'vitest';


describe('App', () => {
  test('it should render Home', async () => {

    const { container } = render(<Home />)
    expect(container.innerHTML).toBe('<div class="w-full"></div>');
  
  });
});
