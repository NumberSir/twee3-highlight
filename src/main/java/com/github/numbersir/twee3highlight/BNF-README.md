### 五分钟自学 BNF 语法

> PSI: Program Structure Interface
> PEG: Parsing Expressions Grammar
> BNF: Backus Normal Form

```bnf
// 基础 PEG BNF 语法
root_rule ::= rule_A rule_B rule_C rule_D                // sequence expression
rule_A ::= token | 'or_text' | "another_one"             // choice expression
rule_B ::= [ optional_token ] and_another_one?           // optional expression
rule_C ::= &required !forbidden                          // predicate expression
rule_D ::= { can_use_braces + (and_parens) * }           // grouping and repetition

// Grammar-Kit BNF 语法
{ generate=[psi="no"] }                                  // top-level global attributes
private left rule_with_modifier ::= '+'                  // rule modifiers
left rule_with_attributes ::= '?' {elementType=rule_D}   // rule attributes

private meta list ::= <<p>> (',' <<p>>) *                // meta rule with parameters
private list_usage ::= <<list rule_D>>                   // meta rule application
```

1. private (PSI tree): skip node creation and let its child nodes be included in its parent.
2. left (PSI tree): take an AST node on the left (previous sibling) and enclose it by becoming its parent.
3. inner (PSI tree): take an AST node on the left (previous sibling) and inject itself into it by becoming its child.
4. upper (PSI tree): take the parent node and replace it by adopting all its children.
5. meta (parser): a parametrized rule; its parse function can take other parse functions as parameters.
6. external (parser): a rule with a hand-written parse function; no parsing code is generated.
7. fake (PSI classes): a rule for shaping the generated PSI classes; only PSI classes are generated.

```bnf
{
  tokens=[
    // token_name=token_value
    // token_name 是 IElementType 类中的常量
    // token_value 用引号包裹
    SEMI=';'
    EQ='='
    LP='('
    RP=')'

    space='regexp:\s+'
    comment='regexp://.*'
    number='regexp:\d+(\.\d*)?'
    id='regexp:\p{Alpha}\w*'
    string="regexp:('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")"

    op_1='+'
    op_2='-'
    op_3='*'
    op_4='/'
    op_5='!'
  ]

  name(".*expr")='expression'
  extends(".*expr")=expr
}

root ::= root_item *
// root 是 root_item重复0~n次
private root_item ::= !<<eof>> property ';' {pin=1 recoverWhile=property_recover}
// root_item 是依次匹配 非eof参数 property规则 半角分号; ，其中一定要匹配非eof参数
// <<param>> 
//   单纯指代参数 param, 这个 param 可能在别处定义
// pin=value:
//   value 可以是数字或其他规则表达式(pin=1 或 pin='rule_A')，用来处理不完全匹配。
//   即只要成功匹配到 pin 指定的一项(如这里的 !<<eof>>)，无论其他项是否匹配都无所谓。
// recoverWhile=value
// name=value
//   value 是字符串

property ::= id '=' expr  {pin=2}
// property 是依次匹配 id 等号= expr规则，其中一定要匹配等号=
private property_recover ::= !(';' | id '=')
// property_recover 是 除了分号;或id 等号

expr ::= factor plus_expr *
// expr 是依次匹配 factor规则 plus_expr规则重复0~n次
left plus_expr ::= plus_op factor
// plus_expr 是依次匹配 plus_op规则 factor规则
private plus_op ::= '+'|'-'
// plus_op 是 加号+或减号-
private factor ::= primary mul_expr *
// factor 是依次匹配 primary规则 mul_expr规则重复0~n次
left mul_expr  ::= mul_op primary
// mul_expr 是依次匹配 mul_op规则 primary规则
private mul_op ::= '*'|'/'
// mul_op 是 乘号*或除号/
private primary ::= primary_inner factorial_expr ?
// primary 是依次匹配 primary_inner规则 factorial_expr规则可有可无
left factorial_expr ::= '!'
// factorial_expr 是 感叹号!
private primary_inner ::= literal_expr | ref_expr | paren_expr
// primary_inner 是 literal_expr规则 或 ref_expr规则 或 paren_expr规则
paren_expr ::= '(' expr ')' {pin=1}
// paren_expr 是依次匹配 左括号( expr规则 右括号) 其中一定要匹配到左括号(
ref_expr ::= id
// ref_expr 是 id
literal_expr ::= number | string | float
// literal_expr 是 number 或 string 或 float
```