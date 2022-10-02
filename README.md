# printer

Printer for the MOZ POS android device.

## Install

```bash
npm install printer
npx cap sync
```

## API

<docgen-index>

* [`print(...)`](#print)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### print(...)

```typescript
print(options: { transaction: any; base64: string; }) => Promise<{ success: boolean; message: string; }>
```

| Param         | Type                                               |
| ------------- | -------------------------------------------------- |
| **`options`** | <code>{ transaction: any; base64: string; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; message: string; }&gt;</code>

--------------------

</docgen-api>
